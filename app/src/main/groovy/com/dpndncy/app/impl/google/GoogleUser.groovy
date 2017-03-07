package com.dpndncy.app.impl.google

/**
 * Created by vaibhav on 07/03/17.
 */
class GoogleUser implements Serializable {
    String kind;
    String etag;
    String gender;
    String objectType;
    String id;
    String displayName;
    String url;
    String language;
    Boolean verified;
    Boolean isPlusUser;
    Integer circledByCount;
    Name name;
    Image image;
    List<Email> emails;

    public static class Name {
        String familyName;
        String givenName;
    }

    public static class Image {
        String url;
        Boolean isDefault;
    }

    public static class Email {
        String value;
        String type;
    }
}
