// package com.samyprojects.rps.futura_back.config;

// import org.apache.catalina.connector.Request;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;

// import com.samyprojects.rps.futura_back.controller.UserController;

// import java.io.BufferedReader;
// import java.util.Collections;

// @Component
// public class RequestLoggingInterceptor implements HandlerInterceptor {

//     private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

//     @Override
//     public boolean preHandle(jakarta.servlet.http.HttpServletRequest  request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
//         // Log headers
//         // System.out.println("=== Request Headers for " + request.getRequestURI() + " ===");
//         logger.info("=== Request Headers for " + request.getRequestURI() + " ===");
//         Collections.list(request.getHeaderNames())
//                 .forEach(headerName ->
//                 //  System.out.println(headerName + ": " + request.getHeader(headerName))
//                 logger.info(headerName + ": " + request.getHeader(headerName))
//                  );

//         // Log body (if present)
//         if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
//             // System.out.println("=== Request Body for " + request.getRequestURI() + " ===");
//             logger.info("=== Request Body for " + request.getRequestURI() + " ===");
//             try {
//                 StringBuilder rawBody = new StringBuilder();
//                 String line;
//                 BufferedReader reader = request.getReader();
//                 while ((line = reader.readLine()) != null) {
//                     rawBody.append(line);
//                 }
//                 // System.out.println(rawBody.toString());
//                 logger.info(rawBody.toString());
//             } catch (Exception e) {
//                 // System.err.println("Error reading request body: " + e.getMessage());
//                 logger.error("Error reading request body: " + e.getMessage());
//             }
//         }

//         return true; // Continue processing the request
//     }
// }