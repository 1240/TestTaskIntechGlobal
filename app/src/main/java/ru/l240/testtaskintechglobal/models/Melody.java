package ru.l240.testtaskintechglobal.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class Melody extends RealmObject {

    private int id;
    private String title;
    private int artistId;
    private String artist;
    private String code;
    private String smsNumber;
    private String price;
    private int fPrice;
    private String prolongationPrice;
    private int fprolongationPrice;
    private String purchasePeriod;
    private int position;
    private String picUrl;
    private String demoUrl;
    private RealmList<Tag> tags;
    private boolean active;
    private int relevance;
    private String melodyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getfPrice() {
        return fPrice;
    }

    public void setfPrice(int fPrice) {
        this.fPrice = fPrice;
    }

    public String getProlongationPrice() {
        return prolongationPrice;
    }

    public void setProlongationPrice(String prolongationPrice) {
        this.prolongationPrice = prolongationPrice;
    }

    public int getFprolongationPrice() {
        return fprolongationPrice;
    }

    public void setFprolongationPrice(int fprolongationPrice) {
        this.fprolongationPrice = fprolongationPrice;
    }

    public String getPurchasePeriod() {
        return purchasePeriod;
    }

    public void setPurchasePeriod(String purchasePeriod) {
        this.purchasePeriod = purchasePeriod;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public RealmList<Tag> getTags() {
        return tags;
    }

    public void setTags(RealmList<Tag> tags) {
        this.tags = tags;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public String getMelodyId() {
        return melodyId;
    }

    public void setMelodyId(String melodyId) {
        this.melodyId = melodyId;
    }
}
