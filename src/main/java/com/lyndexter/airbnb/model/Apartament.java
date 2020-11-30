package com.lyndexter.airbnb.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
public class Apartament {
  private Integer id;
  private Double area;
  private String adress;
  private Double ceilingHigh;
  private Integer roomNumber;
  private Integer recomendedPeopleCount;
  private BigDecimal price;
  private Lessor lessor;
  private Set<ApartamentReserved> apartamentReserveds;
  private Set<Photo> photos;
  private Set<Response> responses;

  public static void printHeaders() {
    System.out.format(
        "%3s    %-8s %-60s %-8s %-6s %-6s %-12s %-6s%n",
        "id",
        "area",
        "adress",
        "ceilingHigh",
        "roomNumber",
        "recomendedPeopleCount",
        "price",
        "lessor");
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

  @Column(name = "area")
  public Double getArea() {
    return area;
  }

  public void setArea(Double area) {
    this.area = area;
  }

  @Column(name = "adress")
  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  @Column(name = "ceiling_high")
  public Double getCeilingHigh() {
    return ceilingHigh;
  }

  public void setCeilingHigh(Double ceilingHigh) {
    this.ceilingHigh = ceilingHigh;
  }

  @Column(name = "room_number")
  public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  @Column(name = "recomended_people_count")
  public Integer getRecomendedPeopleCount() {
    return recomendedPeopleCount;
  }

  public void setRecomendedPeopleCount(Integer recomendedPeopleCount) {
    this.recomendedPeopleCount = recomendedPeopleCount;
  }

  @Column(name = "price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Apartament that = (Apartament) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "lessor_id", referencedColumnName = "id", nullable = false)
  public Lessor getLessor() {
    return lessor;
  }

  public void setLessor(Lessor lessor) {
    this.lessor = lessor;
  }

  @OneToMany(mappedBy = "apartament", fetch = FetchType.LAZY)
  public Set<ApartamentReserved> getApartamentReserveds() {
    return apartamentReserveds;
  }

  public void setApartamentReserveds(Set<ApartamentReserved> apartamentReserveds) {
    this.apartamentReserveds = apartamentReserveds;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "apartament_photo",
      joinColumns =
          @JoinColumn(name = "apartament_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns =
          @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false))
  public Set<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(Set<Photo> photos) {
    this.photos = photos;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "apartament_response",
      joinColumns =
          @JoinColumn(name = "apartament_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns =
          @JoinColumn(name = "response_id", referencedColumnName = "id", nullable = false))
  public Set<Response> getResponses() {
    return responses;
  }

  public void setResponses(Set<Response> responses) {
    this.responses = responses;
  }

  @Override
  public String toString() {
    return String.format(
        "%3s    %-8s %-60s %-8s %-6s %-6s %-12s %-6s%n",
        id, area, adress, ceilingHigh, roomNumber, recomendedPeopleCount, price, lessor.getId());
  }
}
