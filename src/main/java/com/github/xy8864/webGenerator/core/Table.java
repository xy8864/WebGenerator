package com.github.xy8864.webGenerator.core;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: yuanwei
 * Date: 2015.11.13 0013 15:06
 * To change this template use File | Settings | File Templates.
 */
public class Table implements Serializable{
	private static final long serialVersionUID=-1509769104419614749L;
	private Column pk;
	private Set<Column> columns;

}
