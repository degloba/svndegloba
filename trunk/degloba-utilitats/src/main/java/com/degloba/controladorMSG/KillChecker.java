package com.degloba.controladorMSG;


import java.io.File;

/**
 * A KillChecker
 *
 * @author <a href="mailto:tim.fox@jboss.com">Tim Fox</a>
 *
 *
 */
public class KillChecker extends Thread
{
   private final File file;

   public KillChecker(final String dir)
   {
      file = new File(dir + "/KILL_ME");
   }

   @Override
   public void run()
   {
      while (true)
      {
         if (file.exists())
         {
            // Hard kill the VM without running any shutdown hooks

            // Goodbye!

            Runtime.getRuntime().halt(666);
         }

         try
         {
            Thread.sleep(50);
         }
         catch (Exception ignore)
         {
         }
      }
   }
}

