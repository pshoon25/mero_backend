package com.mero.mero_backend.service;

import com.mero.mero_backend.repository.FrameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FrameService {
    private final FrameRepository frameRepository;


}
