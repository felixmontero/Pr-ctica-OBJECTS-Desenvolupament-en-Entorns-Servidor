package com.esliceu.buckets.models;

import java.util.Date;

public class ObjecteVersions {
    private int idObjecte;
    private int idFile;
    private Date date;
    private int version;

    public int getIdObjecte() {
        return idObjecte;
    }

    public void setIdObjecte(int idObjecte) {
        this.idObjecte = idObjecte;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
