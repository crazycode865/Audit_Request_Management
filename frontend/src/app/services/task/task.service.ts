import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskView } from 'src/app/models/task-view';
import { TaskVO } from 'src/app/models/taskVO';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  // private _url = '../../assets/json/task.json';
  private _url = '/api/tasks/';
  private url = '/api/taskVo/taskId/';
  constructor(private _http: HttpClient) {}

  getTasksByReqId(reqId: number): Observable<TaskView[]> {
    // const url = '/assets/json/tasks.json';
    const url = `${this._url}reqId/${reqId}`;
    return this._http.get<TaskView[]>(url);
  }
  getTasks = (): Observable<TaskVO[]> => {
    return this._http.get<TaskVO[]>(this._url);
  };

  getTasksForLoggedInUser = (userName: any): Observable<TaskVO[]> => {
    return this._http.get<TaskVO[]>(this._url.concat(userName));
  };

  getTaskById = (taskId: number): Observable<TaskVO> => {
    return this._http.get<TaskVO>(this.url + `${taskId}`);
  };
}
