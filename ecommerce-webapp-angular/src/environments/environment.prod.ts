export const environment = {
  production: true,
  
  // URL of development API
  // apiUrl: 'http://localhost:3000',
  apiUrl: 'https://wwwdegloba-1350.appspot.com/_ah/api/echo/v1/todocollection',

// https://wwwdegloba-1350.appspot.com/_ah/api/echo/v1/echo
  
  // curl --request POST \
  //    --header "content-type:application/json" \
  //    --data '{"message":"hello world"}' \
  //    "${ENDPOINTS_HOST}/echo?key=${ENDPOINTS_KEY}"
  
  // ENDPOINTS_HOST = https://wwwdegloba-1350.appspot.com
  // ENDPOINTS_KEY = AIzaSyDJFvo6rPvVdWY_uSw3P.........................
  
  
  // curl --request POST --header "content-type:application/json" --data '{"message":"hello world"}' "https://wwwdegloba-1350.appspot.com/echo?key=AIzaSyDJFvo6rPvVdWY_uSw3P...........
   
  firebase: {
        apiKey : 'AIzaSyDuTo74D7zmb6vO6bOV5Z1h7IABIEBM0hM',
        authDomain: 'wwwdegloba-1350.firebaseapp.com',
        databaseURL: 'https://wwwdegloba-1350.firebaseio.com',
        projectId: 'wwwdegloba-1350',
        storageBucket: 'wwwdegloba-1350.appspot.com',
        messagingSenderId : '246205539021'
  }
};