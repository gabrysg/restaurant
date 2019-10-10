package com.restaurant.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ConverterUtils {

	public static <T> Stream<T> getStream(List<T> list) {
		return Optional.ofNullable(list).map(List::stream).orElseGet(Stream::empty);
	}

}
