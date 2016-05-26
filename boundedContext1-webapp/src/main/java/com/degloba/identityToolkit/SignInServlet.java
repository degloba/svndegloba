package com.degloba.identityToolkit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      // This check prevents the "/" handler from handling all requests by default
      if (!"/".equals(request.getServletPath())) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }

      try {
        GitkitUser gitkitUser = null;
        GitkitClient gitkitClient = GitkitClient.createFromJson("gitkit-server-config.json");

        gitkitUser = gitkitClient.validateTokenInRequest(request);
        String userInfo = null;
        if (gitkitUser != null) {
          userInfo = "Welcome back!<br><br> Email: " + gitkitUser.getEmail() + "<br> Id: "
              + gitkitUser.getLocalId() + "<br> Provider: " + gitkitUser.getCurrentProvider();
        }

        response.getWriter().print(new Scanner(new File("templates/index.html"), "UTF-8")
            .useDelimiter("\\A").next()
              .replaceAll("WELCOME_MESSAGE", userInfo != null ? userInfo : "You are not logged in")
            .toString());
        response.setStatus(HttpServletResponse.SC_OK);
      } catch (FileNotFoundException | GitkitClientException | JSONException e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().print(e.toString());
      }
    }
  }