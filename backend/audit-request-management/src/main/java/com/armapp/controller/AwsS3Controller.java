package com.armapp.controller;

import com.amazonaws.HttpMethod;
import com.armapp.model.Assets;
import com.armapp.model.Request;
import com.armapp.model.Task;
import com.armapp.repository.RequestRepository;
import com.armapp.repository.TaskRepository;
import com.armapp.service.AwsS3Service;
import com.armapp.service.IAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dibya Prakash Ojha
 * @date : 18-Jul-22
 * @project : audit-request-management
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AwsS3Controller {

    private AwsS3Service awsS3Service;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private IAssetsService assetsService;

    @Autowired
    public void setAwsS3Service(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    /**
     * This method is responsible for uploading multiple files to AWS S3
     * @param files
     * @param requestId
     * @param taskId
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files, Integer requestId, Integer taskId) {
        String message;
        try {
            List<String> fileNames = new ArrayList<>();
            String filePrefix = null;
            for (MultipartFile file : files) {
                Assets assets = new Assets();
                assets.setCreatedAt(LocalDateTime.now());
                if (requestId != null) {
                    filePrefix = "r" + "_" + requestId + "_";
                    Request request = requestRepository.findById(requestId).get();
                    assets.setRequest(request);
                    assets.setAssetName(filePrefix + file.getOriginalFilename());
                    awsS3Service.uploadFile(file, filePrefix);
                    assetsService.addAssets(assets);
                    fileNames.add(file.getOriginalFilename());
                } else if (taskId != null) {
                    filePrefix = "t" + "_" + taskId + "_";
                    Task task = taskRepository.findById(taskId).get();
                    assets.setTask(task);
                    assets.setAssetName(filePrefix + file.getOriginalFilename());
                    awsS3Service.uploadFile(file, filePrefix);
                    assetsService.addAssets(assets);
                    fileNames.add(file.getOriginalFilename());
                }
            }
            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message = " Failed to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    /**
     *  This method is used to list all the files uploaded in AWS S3
     * @param requestId
     * @param taskId
     * @return List of files uploaded to AWS S3
     */
    @GetMapping("/listFiles")
//    @RolesAllowed({"manager","report_owner"})
    public List<Assets> fileList(@RequestParam(name = "request_id", required = false)
                                         Integer requestId,
                                 @RequestParam(name = "task_id", required = false)
                                         Integer taskId) {

        List<Assets> assetList = assetsService.getAllAssets()
                .stream().filter(asset -> !asset.isDeleted())
                .collect(Collectors.toList());

        List<Assets> filteredAssets = new ArrayList<>();
        assetList.forEach(asset -> {
            if(requestId != null && asset.getRequest().getRequestId().equals(requestId)){
                filteredAssets.add(asset);
            }
            else if(taskId != null && asset.getTask().getTaskId().equals(taskId)){
                filteredAssets.add(asset);
            }
        });

        return filteredAssets;
    }

    /**
     *  This method is responsible for deleting the asset
     * @param fileId
     * @return Message indicating deleting the asset
     */
    @GetMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteFiles(@PathVariable("fileId") Integer fileId){
        assetsService.deleteAsset(fileId);
        return ResponseEntity.ok().body("File Deleted successfully");
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("fileName") String fileName) {
        InputStreamResource resource = new InputStreamResource(awsS3Service.downloadFile((fileName)));
        String fileName1 = fileName.substring(4);
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName1 + "\"")
                .body(resource);
    }

    @GetMapping("/download/task/{taskId}")
    public ResponseEntity<InputStreamResource> downloadFileByTask(@PathVariable("taskId") Integer taskId){
        List<Assets> assets = assetsService.getByTaskId(taskId);
        for (Assets asset : assets) {
            String fileName = asset.getAssetName();
            InputStreamResource resource = new InputStreamResource(awsS3Service.downloadFile((fileName)));
            return ResponseEntity
                    .ok()
                    .cacheControl(CacheControl.noCache())
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/download/request/{requestId}")
    public ResponseEntity<InputStreamResource> downloadFileByRequest(@PathVariable("requestId") Integer requestId){
        List<Assets> assets = assetsService.getByRequestId(requestId);
        for (Assets asset : assets) {
            String fileName = asset.getAssetName();
            InputStreamResource resource = new InputStreamResource(awsS3Service.downloadFile((fileName)));
            return ResponseEntity
                    .ok()
                    .cacheControl(CacheControl.noCache())
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable("fileName") String fileName) {
        return ResponseEntity.ok(awsS3Service.deleteFile(fileName));
    }

    @GetMapping("/generate-upload-url")
    public ResponseEntity<String> generateUploadUrl() {
        return ResponseEntity.ok(
                awsS3Service.generatePreSignedUrl(UUID.randomUUID() + ".txt", "gryffindors-fp", HttpMethod.PUT));
    }
}
