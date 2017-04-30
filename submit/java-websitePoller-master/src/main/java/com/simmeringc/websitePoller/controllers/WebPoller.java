/**
 * Created by Conner on 4/28/17.
 *
 * master polling thread, calls LetterPairSimilarity to
 * get website changes, sends emails, only sends 1 email
 * and newHtml does not replace oldHtml on threshold clear
 */

package com.simmeringc.websitePoller.controllers;

import com.simmeringc.websitePoller.views.TrackerTile;

import static com.simmeringc.websitePoller.controllers.GoogleMail.sendMail;
import static com.simmeringc.websitePoller.controllers.WebRequester.getHtml;
import static com.simmeringc.websitePoller.controllers.LetterPairSimilarity.compareStrings;
import static com.simmeringc.websitePoller.views.MainWindow.trackerTiles;
import static com.simmeringc.websitePoller.views.SystemLog.systemLogDiffDetected;

import java.text.DecimalFormat;

public class WebPoller implements Runnable {
    private int trackerNumber;
    private TrackerTile trackerTile;
    private String url;
    private String email;
    private String oldHtml;
    private String newHtml;
    private double thresholdPercent;
    private double preProcessedPercentDiff;
    private double percentDiff;
    private double percentSimilarity;
    private Boolean emailNotSent;


    public WebPoller(String url, String email, String oldHtml, double thresholdPercent, int trackerNumber) {
        this.url = url;
        this.email = email;
        this.oldHtml = oldHtml;
        this.thresholdPercent = thresholdPercent;
        this.trackerNumber = trackerNumber;
        this.emailNotSent = true;
    }

    public void run() {
        try {
            newHtml = getHtml(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        percentSimilarity = compareStrings(oldHtml, newHtml) * 100;
        setPercentDiff(percentSimilarity);
        trackerTile = trackerTiles.get(trackerNumber);
        if (trackerTile.emailAlertsEnabled() && percentDiff >= thresholdPercent && emailNotSent) {
            systemLogDiffDetected(url, thresholdPercent);
            sendMail(url, email, thresholdPercent);
            emailNotSent = false;
        }
    }

    public double setPercentDiff(double percentSimilarity) {
        double d = (100.00 - percentSimilarity);
        preProcessedPercentDiff = d;
        DecimalFormat df = new DecimalFormat("#.##");
        percentDiff = Double.parseDouble(df.format(d));
        return percentDiff;
    }

    public String getOldHtml() {
        return oldHtml;
    }

    public String getNewHtml() {
        return newHtml;
    }

    public Double getThresholdPercent() {
        return thresholdPercent;
    }

    public double getPreProcessedPercentDiff() {
        return preProcessedPercentDiff;
    }

    public double getPercentDiff() {
        return percentDiff;
    }
}