package br.com.acme.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(length = 30)
	private String title;

	@OneToMany(mappedBy = "task", cascade = ALL)
	private List<TaskItem> items;
	
	public Task() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TaskItem> getItems() {
		return items;
	}

	public void setItems(List<TaskItem> items) {
		this.items = items;
	}

}