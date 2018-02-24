package com.example.easynotes.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

	private ModelMapper modelMapper;

	public DTOMapper() {
		modelMapper = new ModelMapper();
	}

	public <S, T> T map(S source, Class<T> targetClass) {
		return modelMapper.map(source, targetClass);
	}

	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		List<T> list = new ArrayList<>();
		for (S s : source) {
			list.add(modelMapper.map(s, targetClass));
		}
		return list;
	}

	public <S, T> Page<T> mapList(Page<S> source, Pageable pageable, Class<T> targetClass) {
		List<T> list = new ArrayList<>();
		for (S s : source) {
			list.add(modelMapper.map(s, targetClass));
		}

		return new PageImpl<T>(list, pageable, list.size());
	}
}
