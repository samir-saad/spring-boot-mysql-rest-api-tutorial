package com.example.easynotes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.dto.DTOMapper;
import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	DTOMapper mapper;

	@GetMapping("/notes")
	public Page<NoteDto> getAllNotes(Pageable pageRequest) {

		Page<Note> notes = noteRepository.findAll(pageRequest);

		return mapper.mapList(notes, pageRequest, NoteDto.class);
	}

	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId) {
		Note note = noteRepository.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(note);
	}

	@PostMapping("/notes")
	public NoteDto createNote(@Valid @RequestBody NoteDto noteDto) {
		return mapper.map(noteRepository.save(mapper.map(noteDto, Note.class)), NoteDto.class);
	}

	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
			@Valid @RequestBody Note noteDetails) {
		Note note = noteRepository.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteRepository.save(note);
		return ResponseEntity.ok(updatedNote);
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noteRepository.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}

		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
}
