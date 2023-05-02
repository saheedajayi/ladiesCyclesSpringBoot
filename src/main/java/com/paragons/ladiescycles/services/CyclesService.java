package com.paragons.ladiescycles.services;

import com.paragons.ladiescycles.data.models.Cycles;

import java.util.List;

public interface CyclesService  {
    Cycles getOneCycle(Long id);
    List<Cycles> get12Cycles(Long id);
}
