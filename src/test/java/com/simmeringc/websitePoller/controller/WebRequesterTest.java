/**
 * Created by Conner on 4/28/17.
 */

package com.simmeringc.websitePoller.controller;

import static com.simmeringc.websitePoller.controller.WebRequester.getHTML;
import static org.junit.Assert.assertEquals;

public class WebRequesterTest {

    @org.junit.Test
    public void testGetHtml() throws Exception {

        String url = "http://example.org/";

        String html = "<!doctype html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <title>Example Domain</title> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                "  <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\"> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n" +
                "  <style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-color: #f0f0f2;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        font-family: \"Open Sans\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
                "        \n" +
                "    }\n" +
                "    div {\n" +
                "        width: 600px;\n" +
                "        margin: 5em auto;\n" +
                "        padding: 50px;\n" +
                "        background-color: #fff;\n" +
                "        border-radius: 1em;\n" +
                "    }\n" +
                "    a:link, a:visited {\n" +
                "        color: #38488f;\n" +
                "        text-decoration: none;\n" +
                "    }\n" +
                "    @media (max-width: 700px) {\n" +
                "        body {\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "        div {\n" +
                "            width: auto;\n" +
                "            margin: 0 auto;\n" +
                "            border-radius: 0;\n" +
                "            padding: 1em;\n" +
                "        }\n" +
                "    }\n" +
                "    </style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div> \n" +
                "   <h1>Example Domain</h1> \n" +
                "   <p>This domain is established to be used for illustrative examples in documents. You may use this domain in examples without prior coordination or asking for permission.</p> \n" +
                "   <p><a href=\"http://www.iana.org/domains/example\">More information...</a></p> \n" +
                "  </div>   \n" +
                " </body>\n" +
                "</html>";

        assertEquals(html, getHTML(url));
    }
}