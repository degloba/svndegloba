		


		/* Firebase
  		**********************************************************************************************************************
       	* TODO(DEVELOPER): Paste the initialization snippet from: Firebase Console > Overview > Add Firebase to your web app. *
       	***********************************************************************************************************************/
	/////////////<script src="https://www.gstatic.com/firebasejs/3.2.0/firebase.js"></script>

	  // Initialize Firebase
	  var config = {
	    apiKey: "AIzaSyDuTo74D7zmb6vO6bOV5Z1h7IABIEBM0hM",
	    authDomain: "wwwdegloba-1350.firebaseapp.com",
	    databaseURL: "https://wwwdegloba-1350.firebaseio.com",
	    storageBucket: "wwwdegloba-1350.appspot.com",
	  };
	  firebase.initializeApp(config);


  /**
   * Handles the sign in button press. (Login Email)
   */
  function toggleLogInEmail() {
	  
    if (firebase.auth().currentUser) {
      // [START signout]
      firebase.auth().signOut();
      
      // [END signout]
    } else {
      var email = document.getElementById('logInSignupComponent:logInDialegComponent:formLogin:emailLogin').value;
      var password = document.getElementById('logInSignupComponent:logInDialegComponent:formLogin:passwordLogin').value;
           
      // Sign in with email and pass.
      // [START authwithemail]
      firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // [START_EXCLUDE]
        if (errorCode === 'auth/wrong-password') {  
        	PF('growlLogin').renderMessage({"summary":"Notification",
        		  "detail":"Wrong password.",
        		  "severity":"warn"})
        } else {          
          PF('growlLogin').renderMessage({"summary":"Notification",
      		  "detail":errorMessage,
      		  "severity":"warn"})
        }
        console.log(error);       
        // [END_EXCLUDE]
      });
      
      // Ocultem el dialeg
    	$("#logInSignupComponent\\:logInDialegComponent\\:dlgLogin").css("display", "none");
      // [END authwithemail]
    }  
  }

  /**
   * Handles the sign up button press. (Register
   */
  function handleSignUp() {

    var email = document.getElementById('logInSignupComponent:signUpDialegComponent:formSignup:emailSignup').value;
    var password = document.getElementById('logInSignupComponent:signUpDialegComponent:formSignup:passwordSignup').value;
    
    // Sign in with email and pass.
    // [START createwithemail]
    firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
      // Handle Errors here.
      var errorCode = error.code;
      var errorMessage = error.message;
      // [START_EXCLUDE]
      if (errorCode == 'auth/weak-password') {
        PF('growlSignup').renderMessage({"summary":"Warning",
    		  "detail":"The password is too weak.",
    		  "severity":"warn"})    	
      } else {    	 
        ///////alert(errorMessage);
        PF('growlSignup').renderMessage({"summary":"Warning",
  		  "detail":errorMessage,
  		  "severity":"warn"})
      }
      console.log(error);
      // [END_EXCLUDE]
    });
    
    // Ocultem el dialeg
	$("#logInSignupComponent\\:signUpDialegComponent\\:dlgSignup").css("display", "none");
    
    // [END createwithemail]
  }
  /**
   * Sends an email verification to the user.
   */
  function sendEmailVerification() {
    // [START sendemailverification]
    firebase.auth().currentUser.sendEmailVerification().then(function() {
      // Email Verification sent!
      // [START_EXCLUDE]
      
      PF('growlSignup').renderMessage({"summary":"Notification",
  		  "detail":"Email Verification Sent!",
  		  "severity":"warn"})
  		  
      // [END_EXCLUDE]
    });
    // [END sendemailverification]
  }
  function sendPasswordReset() {   	
    var email = document.getElementById('logInSignupComponent:logInDialegComponent:formLogin:emailLogin').value;
    // [START sendpasswordemail]
    firebase.auth().sendPasswordResetEmail(email).then(function() {
      // Password Reset Email Sent!
      // [START_EXCLUDE]
      PF('growlSignup').renderMessage({"summary":"Notification",
  		  "detail":"Password Reset Email Sent!",
  		  "severity":"warn"})
  		  
      // [END_EXCLUDE]
    }).catch(function(error) {
    	
      // Handle Errors here.
      var errorCode = error.code;
      var errorMessage = error.message;
      alert(errorMessage);
      
      // [START_EXCLUDE]
      if (errorCode == 'auth/invalid-email') {
        
        PF('growlSignup').renderMessage({"summary":"Error",
    		  "detail":errorMessage,
    		  "severity":"warn"})
      } else if (errorCode == 'auth/user-not-found') {
       
        PF('growlSignup').renderMessage({"summary":"Error",
  		  "detail":errorMessage,
  		  "severity":"warn"})
      }
      console.log(error);
      // [END_EXCLUDE]
    });
    // [END sendpasswordemail];
  }


  
    /**
     * Function called when clicking the Login/Logout button.
     */
    // [START buttoncallback]
    function toggleLogInGoogle() {
        
    	if (!firebase.auth().currentUser) {
            // [START createprovider]
            var provider = new firebase.auth.GoogleAuthProvider();
            // [END createprovider]
            // [START addscopes]
            provider.addScope('https://www.googleapis.com/auth/plus.login');
            // [END addscopes]
            // [START signin]
            firebase.auth().signInWithPopup(provider).then(function(result) {
              // This gives you a Google Access Token. You can use it to access the Google API.
              var token = result.credential.accessToken;
              // The signed-in user info.
              var user = result.user;
              // [START_EXCLUDE]
              //////document.getElementById('quickstart-oauthtoken').textContent = token;
              // [END_EXCLUDE]
            }).catch(function(error) {
              // Handle Errors here.
              var errorCode = error.code;
              var errorMessage = error.message;
              // The email of the user's account used.
              var email = error.email;
              // The firebase.auth.AuthCredential type that was used.
              var credential = error.credential;
              // [START_EXCLUDE]
              if (errorCode === 'auth/account-exists-with-different-credential') {
                alert('You have already signed up with a different auth provider for that email.');
                // If you are using multiple auth providers on your app you should handle linking
                // the user's accounts here.
              } else {
                console.error(error);
              }
              // [END_EXCLUDE]
            });
            // [END signin]
          } else {
            // [START signout]
            firebase.auth().signOut();
            // [END signout]
          }
          // [START_EXCLUDE]
          //////////document.getElementById('quickstart-sign-in').disabled = true;
          // [END_EXCLUDE]
        }
        // [END buttoncallback]
    
    
    
    function toggleLogInTwitter() {    
      if (!firebase.auth().currentUser) {
        // [START createprovider]
        var provider = new firebase.auth.TwitterAuthProvider();
        // [END createprovider]
        // [START signin]
        firebase.auth().signInWithPopup(provider).then(function(result) {
          // This gives you a the Twitter OAuth 1.0 Access Token and Secret.
          // You can use these server side with your app's credentials to access the Twitter API.
          var token = result.credential.accessToken;
          var secret = result.credential.secret;
          // The signed-in user info.
          var user = result.user;
          // [START_EXCLUDE]
          //document.getElementById('oauthtoken').textContent = token;
          //document.getElementById('oauthsecret').textContent = secret;
          // [END_EXCLUDE]
        }).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          // The email of the user's account used.
          var email = error.email;
          // The firebase.auth.AuthCredential type that was used.
          var credential = error.credential;
          // [START_EXCLUDE]
          if (errorCode === 'auth/account-exists-with-different-credential') {
            alert('You have already signed up with a different auth provider for that email.');
            // If you are using multiple auth providers on your app you should handle linking
            // the user's accounts here.
          } else {
            console.error(error);
          }
          // [END_EXCLUDE]
        });
        // [END signin]
      } else {
        // [START signout]
        firebase.auth().signOut();
        // [END signout]
      }
   // [START_EXCLUDE]
         
      // Ocultem el dialeg
  	$("#signUpDialegComponent\\:dlgSignup").css("display", "none");
      // [END_EXCLUDE]
    }
    // [END buttoncallback]
    
    
    
    /**
     * Function called when clicking the Login/Logout button.
     */
    // [START buttoncallback]
    function toggleLogInFacebook() {
      if (!firebase.auth().currentUser) {
        // [START createprovider]
        var provider = new firebase.auth.FacebookAuthProvider();
        // [END createprovider]
        // [START addscopes]
        provider.addScope('user_birthday');
        // [END addscopes]
        // [START signin]
        firebase.auth().signInWithPopup(provider).then(function(result) {
          // This gives you a Facebook Access Token. You can use it to access the Facebook API.
          var token = result.credential.accessToken;
          // The signed-in user info.
          var user = result.user;
          // [START_EXCLUDE]
          /////////document.getElementById('quickstart-oauthtoken').textContent = token;
          // [END_EXCLUDE]
        }).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code;
          var errorMessage = error.message;
          // The email of the user's account used.
          var email = error.email;
          // The firebase.auth.AuthCredential type that was used.
          var credential = error.credential;
          // [START_EXCLUDE]
          if (errorCode === 'auth/account-exists-with-different-credential') {
            alert('You have already signed up with a different auth provider for that email.');
            // If you are using multiple auth providers on your app you should handle linking
            // the user's accounts here.
          } else {
            console.error(error);
          }
          // [END_EXCLUDE]
        });
        // [END signin]
      } else {
        // [START signout]
        firebase.auth().signOut();
        // [END signout]
      }
      // [START_EXCLUDE]
      ////////////document.getElementById('quickstart-sign-in').disabled = true;
      
      // Ocultem el dialeg
    	$("#signUpDialegComponent\\:dlgSignup").css("display", "none");
      // [END_EXCLUDE]
    }
    // [END buttoncallback]
    
    
    
    
    /**
     * initApp handles setting up the Firebase context and registering
     * callbacks for the auth status.
     *
     * The core initialization is in firebase.App - this is the glue class
     * which stores configuration. We provide an app name here to allow
     * distinguishing multiple app instances.
     *
     * This method also registers a listener with firebase.auth().onAuthStateChanged.
     * This listener is called when the user is signed in or out, and that
     * is where we update the UI.
     *
     * When signed in, we also authenticate to the Firebase Realtime Database.
     */
    function initApp() {
    	$("#logInSignupComponent\\:formUserLoggined\\:userLogginedButton").css("display", "none");
    	$("#google-user").css("display", "none");
    	
    	$("#carregaEntitats").css("display", "none");
    	
    	$("#rentStuffOutputlink").css("display", "none");
    	
    	
      // Result from Redirect auth flow.
      // [START getidptoken]
      firebase.auth().getRedirectResult().then(function(result) {
                   
        if (result.credential) {
          // This gives you a Google Access Token. You can use it to access the Google API.
          var token = result.credential.accessToken;
          // [START_EXCLUDE]
          //document.getElementById('oauthtoken').textContent = token;
        } else {
          //document.getElementById('oauthtoken').textContent = 'null';
          // [END_EXCLUDE]
        }
        // The signed-in user info.
        var user = result.user;
      }).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        // The email of the user's account used.
        var email = error.email;
        // The firebase.auth.AuthCredential type that was used.
        var credential = error.credential;
        // [START_EXCLUDE]
        if (errorCode === 'auth/account-exists-with-different-credential') {
          alert('You have already signed up with a different auth provider for that email.');
          // If you are using multiple auth providers on your app you should handle linking
          // the user's accounts here.
        } else {
          console.error(error);
        }
        // [END_EXCLUDE]
      });
      // [END getidptoken]
      // Listening for auth state changes.
      // [START authstatelistener]
      firebase.auth().onAuthStateChanged(function(user) {    	 
        if (user) {
        	    
        	// Visibilitzem el usuari loginat
        	$("#logInSignupComponent\\:formUserLoggined\\:userLogginedButton").css("display", "inline");        	
        	$("#logInSignupComponent\\:formUserLoggined\\:userLogginedButton").val('user.email');
        	$("#google-user").css("display", "inline");
        	
        	 // Ocultem el dialeg
        	$("#logInSignupComponent\\:signUpDialegComponent\\:dlgSignup").css("display", "none");
        	$("#logInSignupComponent\\:logInDialegComponent\\:dlgLogin").css("display", "none");
        	
        	// Invisibilitzem boto SignUp i LogIn
        	$("#logInSignupComponent\\:btnSignup").css("display", "none");
        	$("#logInSignupComponent\\:btnLogin").css("display", "none");
        	
        	$("#rentStuffOutputlink").css("display", "inline");
        	
        	
        	if (user.email == "degloba@degloba.com") {
        		$("#carregaEntitats").css("display", "block");
        	}
        	else {        		
        		$("#carregaEntitats").css("display", "none");
        	}
        	
        	
        	//User is signed in.
          var displayName = user.displayName;
          var email = user.email;
          var emailVerified = user.emailVerified;
          var photoURL = user.photoURL;
          var isAnonymous = user.isAnonymous;
          var uid = user.uid;          
          var refreshToken = user.refreshToken;
          var providerData = user.providerData;         
          // [START_EXCLUDE]
        
          
          // IMPORTANT !!!!
          // creem cookie per persistir
          document.cookie="uid=" + user.uid;    //????????? The user's ID, unique to the Firebase project. Do NOT use        
          // this value to authenticate with your backend server, if
          // you have one. Use User.getToken() instead.
          // https://firebase.google.com/docs/auth/web/manage-users

			 user.providerData.forEach(function (profile) {
				 
				 if (profile.photoURL != null)
					 {				
						 document.getElementById('google-user').src = profile.photoURL;
						 document.getElementById('logInSignupComponent:nomUsuari').textContent = profile.displayName;
								
		        		//  if (profile.providerId == "google.com") {
		        			  /////////////document.getElementById('sign-in-google').textContent = 'Sign out';
		        		//  } else if (profile.providerId == "twitter.com") {
		        				///////////// document.getElementById('sign-in-twitter').textContent = 'Sign out';
		        		//   } else if (profile.providerId == "facebook.com") {
		        		//	  document.getElementById('sign-in-twitter').textContent = 'Sign out';
		        		//	  alert("facebook"); 
		        		//  };
				 
					 }
        	  }); 
        	//} 
   
          
/*          document.getElementById('account-details').textContent = JSON.stringify({
            displayName: displayName,
            email: email,
            emailVerified: emailVerified,
            photoURL: photoURL,
            isAnonymous: isAnonymous,
            uid: uid,
            refreshToken: refreshToken,
            providerData: providerData
          }, null, '  '); */
          // [END_EXCLUDE]
        } else 
            {              	
        	//Ocultem el usuari loggedOut
        	$("#logInSignupComponent\\:formUserLoggined\\:userLogginedButton").css("display", "none");
        	$("#google-user").css("display", "none");
        	$("#logInSignupComponent\\:nomUsuari").text("");
        	
           	// Visibilitzem botons Signup i Login
        	$("#logInSignupComponent\\:btnSignup").css("display", "block");
        	$("#logInSignupComponent\\:btnLogin").css("display", "block");
        	
        	
        	$("#carregaEntitats").css("display", "none");
        	$("#rentStuffOutputlink").css("display", "none");
        	
          // User is signed out.
          // [START_EXCLUDE]
          //document.getElementById('sign-in-status').textContent = 'Signed out';
          ///////////document.getElementById('sign-in-google').textContent = 'Sign in with Google';
          //document.getElementById('account-details').textContent = 'null';
          //document.getElementById('oauthtoken').textContent = 'null';
          // [END_EXCLUDE]
        }
        // [START_EXCLUDE]
        //document.getElementById('sign-in-google').disabled = false;
        //document.getElementById('sign-in-twitter').disabled = false;
        // [END_EXCLUDE]
      });
      // [END authstatelistener]
      //document.getElementById('sign-in-google').addEventListener('click', toggleSignInGoogle2, false);
      //document.getElementById('sign-in-twitter').addEventListener('click', toggleSignInTwitter, false);
    }
    window.onload = function() {
    
      initApp();
    };

    
    function handleLoginRequest(xhr, status, args) {

        alert(args.signUPType);
        
        if(args.validationFailed || !args.signUpExist) {
            PF('dlgSignup').jq.effect("shake", {times:5}, 100);
        }
        else {
            PF('dlgSignup').hide();
            $('#loginLink').fadeOut();

            toggleLogInGoogle();
        }
    }
    
