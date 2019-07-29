package com.degloba.canals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public class clientTwitter {
	
	static Twitter twitter;
	
	public clientTwitter()
	{
	
	}
	
	
	// Afegeix un tweet
	public static void updateWhatAreYouDoing(String latestStatus)
	{
		
		// The factory instance is re-useable and thread safe.
	    twitter = new TwitterFactory().getInstance();
	    Status status;
		try {
			status = twitter.updateStatus(latestStatus);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void getPublicTimeline()
	{
		 // The factory instance is re-useable and thread safe.
	    twitter = new TwitterFactory().getInstance();
	    List<Status> statuses = null;
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Showing public timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	
	
	public static void getTimelineFriends()
	{
		 // The factory instance is re-useable and thread safe.
	    twitter = new TwitterFactory().getInstance();
	    List<Status> statuses = null;
		try {
			statuses = twitter.getUserTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Showing friends timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	    
	
	public static void getTimelineUser()
	{
		 try {
	            User user = twitter.verifyCredentials();
	            List<Status> statuses = twitter.getUserTimeline();
	            System.out.println("Showing @" + user.getScreenName() + "'s user timeline.");
	            for (Status status : statuses) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	            }
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get timeline: " + te.getMessage());
	            System.exit(-1);
	        }
	    
	}

	
	public void sendReceiveMessages(String recipientId, String text)
	{
		
		 // The factory instance is re-useable and thread safe.
	    Twitter sender = new TwitterFactory().getInstance();
	    DirectMessage message;
		try {
			message = sender.sendDirectMessage(recipientId, text);
			System.out.println("Sent: " + message.getText() + " to @" + message.getRecipientScreenName());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	}

	
	public static void searchTweets()
	{
	    // The factory instance is re-useable and thread safe.
	    twitter = new TwitterFactory().getInstance();
	    
	    Query query = new Query("CLOUD JBOSS");
	    QueryResult result;
		try {
			result = twitter.search(query);
			System.out.println("hits:" + result.getCompletedIn());
			    for (Status tweet : result.getTweets()) {
			        System.out.println(tweet.getUser() + ":" + tweet.getText());
			    }
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
		        
	public static void followers()
	{
	   try {
	           long cursor = -1;
	           IDs ids;
	           System.out.println("Listing following ids.");
	           do {
	                //////////ids = twitter.getFriendsIDs(cursor);
	                ids = twitter.getFollowersIDs(1);   //PERE
	                for (long id : ids.getIDs()) {
	                    System.out.println(id);
	                }
	            } while ((cursor = ids.getNextCursor()) != 0);
	           /// System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to get friends' ids: " + te.getMessage());
	            System.exit(-1);
	        }
		
		}		


		public static void userLists()
		{
			twitter = new TwitterFactory().getInstance();
	        try {
	         
	            long cursor = -1;
	            PagableResponseList<User> lists;
	            do {
	                lists = twitter.getUserListMembers(cursor, "degloba", cursor);
	                for (User list : lists) {
	                    System.out.println("id:" + list.getId() + ", name:" + list.getName() + ", description:");
	                           // + list.getDescripcio() + ", slug:" + list.getSlug() + "");
	                }
	            } while ((cursor = lists.getNextCursor()) != 0);
	           // System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to list the lists: " + te.getMessage());
	            System.exit(-1);
	        }
		}
	        
	        
//NOMES S'HA D'EXECUTAR UNA VEGADA SI EL twitter4j.properties NO CONT� ELS ACCES TOKEN I ACCESS TOKEN SECRET 
		
		public static void connect()
		{

	        File file = new File("twitter4j.properties");
	        Properties prop = new Properties();
	        InputStream is = null;
	        OutputStream os = null;
	        try{
	            if (file.exists()) {
	                is = new FileInputStream(file);
	                prop.load(is);
	            }
	        }catch(IOException ioe){
	            ioe.printStackTrace();
	            System.exit(-1);
	        }finally{
	            if(null != is){
	                try {
	                    is.close();
	                } catch (IOException ignore) {
	                }
	            }
	            if(null != os){
	                try {
	                    os.close();
	                } catch (IOException ignore) {
	                }
	            }
	        }
	        
	        try {
	            twitter = new TwitterFactory().getInstance();
	            RequestToken requestToken = twitter.getOAuthRequestToken();  // 1
	            System.out.println("Got request token.");
	            System.out.println("Request token: "+ requestToken.getToken());
	            System.out.println("Request token secret: "+ requestToken.getTokenSecret());
	            AccessToken accessToken = null;

	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            while (null == accessToken) {
	                System.out.println("Open the following URL and grant access to your account:");
	                System.out.println(requestToken.getAuthorizationURL()); //2
	                System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
	                String pin = br.readLine();
	                try{
	                    if(pin.length() > 0){
	                        accessToken = twitter.getOAuthAccessToken(requestToken, pin);  //3
	                    }else{
	                        accessToken = twitter.getOAuthAccessToken(requestToken); //3
	                    }
	                } catch (TwitterException te) {
	                    if(401 == te.getStatusCode()){
	                        System.out.println("Unable to get the access token.");
	                    }else{
	                        te.printStackTrace();
	                    }
	                }
	            }
	            System.out.println("Got access token.");
	            System.out.println("Access token: " + accessToken.getToken());
	            System.out.println("Access token secret: " + accessToken.getTokenSecret());

	            try {
	                prop.setProperty("oauth.accessToken", accessToken.getToken());
	                prop.setProperty("oauth.accessTokenSecret", accessToken.getTokenSecret());
	                os = new FileOutputStream(file);
	                prop.store(os, "twitter4j.properties");
	                os.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	                System.exit(-1);
	            } finally {
	                if (null != os) {
	                    try {
	                        os.close();
	                    } catch (IOException ignore) {
	                    }
	                }
	            }
	            System.out.println("Successfully stored access token to " + file.getAbsolutePath()+ ".");
	            System.exit(0);
	        } catch (TwitterException te) {
	            System.out.println("Failed to get accessToken: " + te.getMessage());
	            System.exit( -1);
	        } catch (IOException ioe) {
	            System.out.println("Failed to read the system input.");
	            System.exit( -1);
	        }
		}


		
		public static void main(String[] args) {
			
			/*try {
				connect();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			connect();
			
			userLists();  // LLISTES DEFINIDES (EX: @degloba/cloud , @degloba/soa , ...)
			followers();  // SEGUIDORS
			getTimelineUser();
			getPublicTimeline();
			
			//getTimelineFriends();
			//updateWhatAreYouDoing("fent proves");  // afegint tweets
			//searchTweets();
			//sendReceiveMessages(String recipientId, String text)
		}
	
	
}
