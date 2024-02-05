
<<<<<<< HEAD
# Login-page

![Screenshot from 2024-02-05 13-28-10](https://github.com/Tannu-Kamat/Login-page/assets/110116827/c6bdeb7b-b5dd-454d-9cdd-f6d635c68479)

# forgot-password-page

![Screenshot from 2024-02-05 13-28-37](https://github.com/Tannu-Kamat/Login-page/assets/110116827/0ba8f02e-b2d3-4d2a-9ee9-4ab092040f42)

# page-not-found

![Screenshot from 2024-02-05 13-29-11](https://github.com/Tannu-Kamat/Login-page/assets/110116827/20b09ddc-fa90-40a3-84d9-924e8fdd378e)


---



___
___

# Use of ng-bootstrap

ng-bootstrap is a popular open-source UI component library for Angular. It simplifies the process of building beautiful and functional web applications by providing a rich set of pre-built components.

Getting Started

1. Install Bootstrap :-
```
npm install bootstrap
```
---
2. Update angular.json:-
Open your angular.json file and add the path to Bootstrap's CSS file in the "styles" array.

``` js
"styles": [
  "node_modules/bootstrap/dist/css/bootstrap.min.css",
  "src/styles.css"
]
```
---
3. Import the NgbModule:-
In app.module.ts , import the NgbModule.


``` js
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [NgbModule],
  // ...
})

```

-------


# MyApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 17.0.8.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
>>>>>>> e80072d (first commit)
