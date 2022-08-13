# AuditRequestManagement

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.0.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Authentication

You can create a Keycloak server in local using the following command 

`docker run -p 7688:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.2 start-dev`

Create a realm `myrealm` and client `myclient` or change accordingly in keycloak.config.ts file

## Reference Links 

- [Material Palette Generator](http://mcg.mbitson.com/#!?mcgpalette0=%233f51b5)
- [Keycloak SPA](https://javascript.plainenglish.io/secure-an-angular-single-page-application-with-keycloak-cdbe5026881e)
- [Keycloak Docker](https://www.keycloak.org/getting-started/getting-started-docker)
- [Theming](https://material.angular.io/guide/theming-your-components)
- [Icon List](https://www.angularjswiki.com/angular/angular-material-icons-list-mat-icon-list/)

## Code Quality 
1. Install ESLint Extension
2. Add the following in settings.json
```
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true 
    },
```

## Commit Structure 

`[JIRA_ID] <Commit Message>`
