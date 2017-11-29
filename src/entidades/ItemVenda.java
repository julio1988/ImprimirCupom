/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Julio
 */
@Entity
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String produto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private BigDecimal vlUnitario;
    @ManyToOne
    private Venda venda;

    public ItemVenda() {
        this.quantidade = 0;
        this.vlUnitario = BigDecimal.ZERO;
    }

    public ItemVenda(Produto produto, Venda venda) {
        this.produto = produto.getNome();
        this.precoUnitario = produto.getPreco();
        this.vlUnitario = produto.getPreco();
        this.quantidade = 0;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(BigDecimal vlUnitario) {
        this.vlUnitario = vlUnitario;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + produto + " | Qtdade: " + quantidade + " | Vl Unt: " + getVlUnitario() + " | Total: " + getTotal();
    }

    public String getTotal() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String total = df.format(this.vlUnitario.multiply(BigDecimal.valueOf(quantidade)));
        return total;
    }

    public BigDecimal getVlTotal() {
        return this.vlUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
