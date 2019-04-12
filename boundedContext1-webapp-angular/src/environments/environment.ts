// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  
   // URL of development API
   // apiUrl: 'http://localhost:3000',   // AQUEST SEMBLA QUE TAMBÉ FUNCIONA QUAN DEPLOY A GCP
  apiUrl: 'https://wwwdegloba-1350.appspot.com/_ah/api/echo/v1/todocollection',

  

    firebase: {
        apiKey : 'AIzaSyDuTo74D7zmb6vO6bOV5Z1h7IABIEBM0hM',
        authDomain: 'wwwdegloba-1350.firebaseapp.com',
        databaseURL: 'https://wwwdegloba-1350.firebaseio.com',
        projectId: 'wwwdegloba-1350',
        storageBucket: 'wwwdegloba-1350.appspot.com',
        messagingSenderId : 'xxxxxxx'
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
