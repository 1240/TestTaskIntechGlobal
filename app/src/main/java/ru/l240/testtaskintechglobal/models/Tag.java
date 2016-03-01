package ru.l240.testtaskintechglobal.models;

import io.realm.RealmObject;

/**
 * @author Alexander Popov created on 01.03.2016.
 */
public class Tag extends RealmObject {

    private int id;
    private String title;
    private boolean limitedVisibility;
    private int position;
    private boolean isFullCatalogEnabled;
    private int topMelodiesCount;
    private boolean isBlockDisplayMode;

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

    public boolean isLimitedVisibility() {
        return limitedVisibility;
    }

    public void setLimitedVisibility(boolean limitedVisibility) {
        this.limitedVisibility = limitedVisibility;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFullCatalogEnabled() {
        return isFullCatalogEnabled;
    }

    public void setFullCatalogEnabled(boolean fullCatalogEnabled) {
        isFullCatalogEnabled = fullCatalogEnabled;
    }

    public int getTopMelodiesCount() {
        return topMelodiesCount;
    }

    public void setTopMelodiesCount(int topMelodiesCount) {
        this.topMelodiesCount = topMelodiesCount;
    }

    public boolean isBlockDisplayMode() {
        return isBlockDisplayMode;
    }

    public void setBlockDisplayMode(boolean blockDisplayMode) {
        isBlockDisplayMode = blockDisplayMode;
    }
}
