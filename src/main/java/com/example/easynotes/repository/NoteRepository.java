package com.example.easynotes.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.easynotes.model.Note;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

}
