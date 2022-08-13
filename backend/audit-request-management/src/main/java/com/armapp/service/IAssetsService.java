package com.armapp.service;

import com.armapp.model.Assets;

import java.util.List;

/**
 * @author Dibya Prakash Ojha
 * @date : 23-Jul-22
 * @project : audit-request-management
 */
public interface IAssetsService {
    void addAssets(Assets assets);

    List<Assets> getAllAssets();

    List<Assets> getByTaskId(Integer taskId);

    List<Assets> getByRequestId(Integer requestId);

    void deleteAsset(Integer assetId);
}
