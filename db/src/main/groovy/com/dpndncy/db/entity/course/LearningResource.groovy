package com.dpndncy.db.entity.course

import com.dpndncy.db.entity.Auditable
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Lob
import javax.persistence.Table

/**
 * Created by vaibhav on 18/02/17.
 */
@Entity
@Table(name = 'learning_resources')
@ToString
class LearningResource extends Auditable implements Serializable {

    String name;
    @Lob
    String description;
    String link;
    @Enumerated(value = EnumType.STRING)
    LearningResourceType type;

    public static enum LearningResourceType {
        BOOK, VIDEO_LECTURES, AUDIO_LECTURES
    }
}
