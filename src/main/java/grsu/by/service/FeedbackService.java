package grsu.by.service;

import grsu.by.dto.FeedbackDto;

public interface FeedbackService {

    FeedbackDto create(FeedbackDto dto);

    FeedbackDto findById(Long id);

    FeedbackDto update(Long id, FeedbackDto newDto);

    boolean deleteById(Long id);

}
