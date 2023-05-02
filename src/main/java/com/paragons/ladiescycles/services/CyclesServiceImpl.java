package com.paragons.ladiescycles.services;

import com.paragons.ladiescycles.data.models.Cycles;
import com.paragons.ladiescycles.data.models.Lady;
import com.paragons.ladiescycles.data.repositories.CyclesRepository;
import com.paragons.ladiescycles.data.repositories.LadyRepository;
import com.paragons.ladiescycles.exceptions.LadyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class CyclesServiceImpl implements CyclesService {
    private final LadyRepository ladyRepository;
    private final CyclesRepository cyclesRepository;

    @Override
    public Cycles getOneCycle(Long id) {
        return getCycles(id);
    }

    @Override
    public List<Cycles> get12Cycles(Long id) {
        List<Cycles> months = new ArrayList<>();
        months.add(getCycles(id));

        for(int i = 1; i < 12; i++){
            Cycles nextCycle = months.get(months.size()-1);
            LocalDate nextPeriod = nextCycle.getNextPeriodStart();
            LocalDate nextPeriodStart = nextPeriod.plusDays(menstrualCycleLength(id));

            List<LocalDate> flowDaysNum = new ArrayList<>();
            List<LocalDate> flowDays = nextCycle.getFlowDaysNum();
            for(LocalDate flowDay: flowDays){
                flowDaysNum.add(flowDay.plusDays(menstrualCycleLength(id)));
            }

            LocalDate ovulationDate = nextPeriodStart.plusDays(menstrualCycleLength(id)/2);

            List<LocalDate> fertilePeriods = new ArrayList<>();

            for(int j = 3; j > 0; j-- ){
                fertilePeriods.add(ovulationDate.minus(Period.ofDays(j)));
            }
            for(int j = 1; j <= 3; j++ ){
                fertilePeriods.add(ovulationDate.plus(Period.ofDays(j)));
            }

            Cycles cycles = new Cycles();
            cycles.setNextPeriodStart(nextPeriodStart);
            cycles.setFlowDaysNum(flowDaysNum);
            cycles.setOvulationDate(ovulationDate);
            cycles.setFertilePeriods(fertilePeriods);

            months.add(cycles);

        }
        return months;
    }

    private Cycles getCycles(Long id){
        Lady lady = ladyRepository.findById(id).orElseThrow(()-> new LadyNotFoundException("The user does not exist"));
        LocalDate nextPeriodStart = nextPeriod(lady);
        List<LocalDate> flowDaysNum = flowDays(lady);
        LocalDate ovulationDate = ovulationDate(lady);
        List<LocalDate> fertilePeriods = fertilePeriods(lady);

        Cycles cycles = new Cycles();
        cycles.setNextPeriodStart(nextPeriodStart);
        cycles.setFlowDaysNum(flowDaysNum);
        cycles.setOvulationDate(ovulationDate);
        cycles.setFertilePeriods(fertilePeriods);

        return cyclesRepository.save(cycles);
    }


    private List<LocalDate> fertilePeriods(Lady lady){
        List<LocalDate> fertilePeriods = new ArrayList<>();
        LocalDate ovulationDate = ovulationDate(lady);

        for(int i = 3; i > 0; i-- ){
            fertilePeriods.add(ovulationDate.minus(Period.ofDays(i)));
        }
        for(int i = 1; i <= 3; i++ ){
            fertilePeriods.add(ovulationDate.plus(Period.ofDays(i)));
        }

        return fertilePeriods;
    }

    private LocalDate ovulationDate(Lady lady){
        LocalDate cycleDateStart =  splitDate(lady.getCycleStartDate());
        LocalDate cycleDateEnd = splitDate(lady.getCycleEndDate());

        int menstrualCycleLength = Period.between(cycleDateStart, cycleDateEnd).getDays();
        return nextPeriod(lady).plus(Period.ofDays(menstrualCycleLength/2));
    }

    private List<LocalDate> flowDays(Lady lady){
        List<LocalDate> flowDays = new ArrayList<>();
        LocalDate flowDateStart =  splitDate(lady.getCycleStartDate());
        LocalDate flowDateEnd = splitDate(lady.getFlowEndDate());

        int days = Period.between(flowDateStart, flowDateEnd).getDays();

        for (int i = 0; i < days ; i++) {
            Period day = Period.ofDays(i);
            flowDays.add(nextPeriod(lady).plus(day));
        }
        return flowDays;
    }

    private LocalDate nextPeriod(Lady lady){
        LocalDate cycleDateStart =  splitDate(lady.getCycleStartDate());
        LocalDate cycleDateEnd = splitDate(lady.getCycleEndDate());

        int menstrualCycleLength =  Period.between(cycleDateStart, cycleDateEnd).getDays();
        Period days = Period.ofDays(menstrualCycleLength + 1);
        return cycleDateEnd.plus(days);
    }

    private int menstrualCycleLength(Long id){
        Lady lady = ladyRepository.findById(id).orElseThrow(()-> new LadyNotFoundException("The user does not exist"));

        LocalDate cycleDateStart =  splitDate(lady.getCycleStartDate());
        LocalDate cycleDateEnd = splitDate(lady.getCycleEndDate());

        return Period.between(cycleDateStart, cycleDateEnd).getDays();
    }

    private LocalDate splitDate(String dateInput){
        String[] date = dateInput.split("-");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        return LocalDate.of(year, month, day);
    }


}
