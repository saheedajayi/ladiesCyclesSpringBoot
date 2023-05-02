package com.paragons.ladiescycles.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cycles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate nextPeriodStart;
    @ElementCollection
    private List<LocalDate> flowDaysNum;
    private LocalDate ovulationDate;
    @ElementCollection
    private List<LocalDate> fertilePeriods;
}
