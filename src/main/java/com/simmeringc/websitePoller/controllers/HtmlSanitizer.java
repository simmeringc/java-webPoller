/**
 * Created by simmeringc on 4/30/17.
 *
 * https://github.com/OWASP/java-html-sanitizer
 * contains policies for filtering HTML get()
 *
 * print filteredHtml to see the HTML that the application is diffing
 */

package com.simmeringc.websitePoller.controllers;

import org.owasp.html.*;

public class HtmlSanitizer {

    //a policy definition that matches text in most HTML, some websites need custom rules to access elements
    public static String defaultPolicy(String str) {
        PolicyFactory policy = new HtmlPolicyBuilder()
                .allowStandardUrlProtocols()
                .allowTextIn("body", "div", "a", "p", "div", "i", "b", "em", "blockquote", "tt", "strong",
                        "br", "ul", "ol", "li", "iframe", "textarea")
                .toFactory();
        //string cleaning, sanitize with policy, uglify to remove newlines and leading/trailing whitespace
        String filteredHtml = policy.sanitize(str);
        filteredHtml = filteredHtml.replaceAll("^\\s+","");
        filteredHtml = filteredHtml.replaceAll("\\s+$","");
        filteredHtml = filteredHtml.replaceAll("\\n+","");
        return filteredHtml;
    }
}