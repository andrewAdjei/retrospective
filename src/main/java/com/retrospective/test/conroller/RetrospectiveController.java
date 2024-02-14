package com.retrospective.test.conroller;

import java.util.List;

import com.retrospective.test.entity.FeedbackItem;
import com.retrospective.test.entity.Retrospective;
import com.retrospective.test.exception.RetrospectiveNotFoundException;
import com.retrospective.test.service.RetroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/")
public class RetrospectiveController {

    @Autowired
    private RetroServiceImpl retroService;

    private final Logger logger = Logger.getLogger(RetrospectiveController.class.getName());

    @PostMapping(value = "v1/retrospective")
    public ResponseEntity<Retrospective> createRetrospective(@RequestBody Retrospective retrospective) {
        try {
            Retrospective createdRetrospective = retroService.save(retrospective);
            return new ResponseEntity<>(createdRetrospective, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating retrospective", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "v1/retrospective")
    public ResponseEntity<List<Retrospective>> getAllRetrospectives() {
        try {
            List<Retrospective> retrospectives = retroService.findAll();
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting retrospectives", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "v1/retrospective/sort")
    public ResponseEntity<List<Retrospective>> findAllRetrospectiveBySort() {
        try {
            List<Retrospective> retrospectives = retroService.findAllSort();
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting retrospectives by sort", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "v1/retrospective/page/{page}/{size}")
    public ResponseEntity<Page<Retrospective>>  findAllWithPagination(@PathVariable int page, @PathVariable int size) {
        try {
            Page<Retrospective> retrospectives = retroService.findAllWithPagination(page,size);
            return new ResponseEntity<>(retrospectives, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting retrospectives by pagination", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "v1/retrospective/{name}")
    public ResponseEntity<List<Retrospective>> findByNameAndSort(@PathVariable String name) {
        return new ResponseEntity<List<Retrospective>>(retroService.findByNameAndSort(name), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Retrospective> getRetrospectiveByName(@PathVariable String name) {
        try {
            Retrospective retrospective = retroService.findByName(name);
            return new ResponseEntity<>(retrospective, HttpStatus.OK);
        } catch (RetrospectiveNotFoundException e) {
            logger.log(Level.INFO, "Retrospective not found: " + name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting retrospective", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{retrospectiveName}/feedback")
    public ResponseEntity<Retrospective> addFeedbackItemToRetrospective(
            @PathVariable String retrospectiveName,
            @RequestBody FeedbackItem feedbackItem) {

        Retrospective retrospective = retroService.findByName(retrospectiveName);

        if (retrospective != null) {
            retrospective.getFeedbackItems().add(feedbackItem);
            retroService.save(retrospective);
            return new ResponseEntity<>(retrospective, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    // Update Feedback Item
    @PutMapping("v1/{retrospectiveName}/feedback/{feedbackItemId}")
    public ResponseEntity<Retrospective> updateFeedbackItem(
            @PathVariable String retrospectiveName,
            @PathVariable Long feedbackItemId,
            @RequestBody FeedbackItem updatedFeedbackItem) {

        Retrospective retrospective = retroService.findByName(retrospectiveName);

        if (retrospective != null) {
            for (FeedbackItem existingFeedbackItem : retrospective.getFeedbackItems()) {
                if (existingFeedbackItem.getId().equals(feedbackItemId)) {
                    existingFeedbackItem.setBody(updatedFeedbackItem.getBody());
                    existingFeedbackItem.setFeedbackType(updatedFeedbackItem.getFeedbackType());
                    retroService.save(retrospective);
                    return new ResponseEntity<>(retrospective, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
