package hu.virgo.courses.hibernate.lesson07.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
@NamedEntityGraph(name = "graph.Order.items",
		attributeNodes = {
			@NamedAttributeNode(value = "items", subgraph = "items")
		},
		subgraphs = {
				@NamedSubgraph(name = "items", attributeNodes = @NamedAttributeNode("product"))
		})
public class Order {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id = null;

	@Column
	private String orderNumber;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private Set<OrderItem> items = new HashSet<OrderItem>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
}
