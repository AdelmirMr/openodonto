package br.ueg.openodonto.persistencia.orm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MaskResolver implements ResultMask {

	private Class<?> classe;
	private List<String> fields;

	public MaskResolver(Class<?> classe, String... fields) {
		this(classe,Arrays.asList(fields));
	}

	public MaskResolver(Class<?> classe, List<String> fields) {
		this.classe = classe;
		this.fields = fields;
	}

	@Override
	public List<Field> getResultMask() {
		List<Field> list = OrmResolver.getAllFields(new LinkedList<Field>(),classe, true);
		return filterFields(list);
	}

	@Override
	public List<Field> getResultMask(Class<?> classe) {
		List<Field> list = OrmResolver.getAllFields(new LinkedList<Field>(),classe, false);
		return filterFields(list);
	}

	public List<Field> filterFields(List<Field> list) {
		List<Field> remove = new ArrayList<Field>();
		Iterator<Field> iterator = list.iterator();
		while (iterator.hasNext()) {
			Field field = iterator.next();
			if (!fields.contains(field.getName())) {
				remove.add(field);
			}
		}
		for (Field field : remove) {
			list.remove(field);
		}
		return list;
	}

	public List<String> getFields() {
		return fields;
	}

}
