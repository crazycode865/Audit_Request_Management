import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from 'src/app/models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  constructor(private _httpClient: HttpClient) {}

  getAllProjects = (): Observable<Project[]> => {
    const url = '/api/projects';
    return this._httpClient.get<Project[]>(url);
  };
  getProjectsOfTypedProduction = (productionId: any): Observable<Project[]> => {
    return this._httpClient.get<Project[]>(
      `/api/projectNames/productionId/${productionId}`
    );
  };
}
