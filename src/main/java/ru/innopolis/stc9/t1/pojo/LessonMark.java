/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.innopolis.stc9.t1.pojo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author ssspokd
 */
@Entity
@Table(name = "lesson_mark")
@XmlRootElement
public class LessonMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lm_id")
    private Integer lmId;
    @Basic(optional = false)
    @Column(name = "lsn_id")
    private int lsnId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "visit")
    private boolean visit;
    @Basic(optional = false)
    @Column(name = "mark")
    private float mark;

    public LessonMark() {
    }

    public LessonMark(Integer lmId) {
        this.lmId = lmId;
    }

    public LessonMark(Integer lmId, int lsnId, int userId, boolean visit, float mark) {
        this.lmId = lmId;
        this.lsnId = lsnId;
        this.userId = userId;
        this.visit = visit;
        this.mark = mark;
    }

    public LessonMark( int lsnId, int userId, boolean visit, float mark) {
        this.lsnId = lsnId;
        this.userId = userId;
        this.visit = visit;
        this.mark = mark;
    }

    public Integer getLmId() {
        return lmId;
    }

    public void setLmId(Integer lmId) {
        this.lmId = lmId;
    }

    public int getLsnId() {
        return lsnId;
    }

    public void setLsnId(int lsnId) {
        this.lsnId = lsnId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lmId != null ? lmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LessonMark)) {
            return false;
        }
        LessonMark other = (LessonMark) object;
        if ((this.lmId == null && other.lmId != null) || (this.lmId != null && !this.lmId.equals(other.lmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.innopolis.stc9.t1.pojo.LessonMark[ lmId=" + lmId + " ]";
    }
    
}
