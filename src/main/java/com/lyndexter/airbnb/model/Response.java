package com.lyndexter.airbnb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Response {
  private Integer id;
  private String coment;
  private Integer rate;
  private Set<Apartament> apartaments;
  private Set<Photo> photos;

  public static void printHeaders() {
    System.out.format("%3s    %-12s %-40s %n", "id", "rate", "coment");
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "coment")
  public String getComent() {
    return coment;
  }

  public void setComent(String coment) {
    this.coment = coment;
  }

  @Column(name = "rate")
  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Response response = (Response) o;

    return Objects.equals(id, response.id);
  }

  @Override
  public int hashCode() {

    return id != null ? id.hashCode() : 0;
  }

  @ManyToMany(mappedBy = "responses")
  public Set<Apartament> getApartaments() {
    return apartaments;
  }

  public void setApartaments(Set<Apartament> apartaments) {
    this.apartaments = apartaments;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "response_photo",
      joinColumns =
          @JoinColumn(name = "response_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns =
          @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false))
  public Set<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(Set<Photo> photos) {
    this.photos = photos;
  }

  @Override
  public String toString() {
    return String.format("%3s    %-12s %-40s %n", id, rate, coment);
  }
}
