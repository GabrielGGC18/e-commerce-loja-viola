package br.unitins.tp1.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.tp1.model.Viola;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViolaRepository implements PanacheRepository<Viola> {

    public List<Viola> findByModelo(String modelo) {
        return find("upper(modelo) LIKE upper(?1)", "%" + modelo + "%").list();
    }

    public List<Viola> findByMarca(String marca) {
        return find("upper(marca) LIKE upper(?1)", "%" + marca + "%").list();
    }

    public List<Viola> findByAfinacao(String afinacao) {
        return find("upper(afinacao) LIKE upper(?1)", "%" + afinacao + "%").list();
    }

    public List<Viola> findByPreco(Double precoMinimo, Double precoMaximo) {
        return find("preco >= ?1 AND preco <= ?2", precoMinimo, precoMaximo).list();
    }

    public List<Viola> findByCordas(Integer cordasMinimo, Integer cordasMaximo) {
        return find("cordas >= ?1 AND cordas <= ?2", cordasMinimo, cordasMaximo).list();
    }

    public List<Viola> findByFabricacao(LocalDate inicio, LocalDate fim) {
        return find("fabricacao >= ?1 AND fabricacao <= ?2", inicio, fim).list();
    }

    public List<Viola> findByFiltros(String modelo, String marca, String afinacao,
            Double precoMinimo, Double precoMaximo, Integer cordasMinimo, Integer cordasMaximo,
            LocalDate fabricadaApos, LocalDate fabricadaAntes) {

        StringBuilder jpql = new StringBuilder("1 = 1");
        Map<String, Object> params = new HashMap<>();

        if (modelo != null && !modelo.isBlank()) {
            jpql.append(" AND upper(modelo) LIKE upper(:modelo)");
            params.put("modelo", "%" + modelo + "%");
        }
        if (marca != null && !marca.isBlank()) {
            jpql.append(" AND upper(marca) LIKE upper(:marca)");
            params.put("marca", "%" + marca + "%");
        }
        if (afinacao != null && !afinacao.isBlank()) {
            jpql.append(" AND upper(afinacao) LIKE upper(:afinacao)");
            params.put("afinacao", "%" + afinacao + "%");
        }
        if (precoMinimo != null) {
            jpql.append(" AND preco >= :precoMinimo");
            params.put("precoMinimo", precoMinimo);
        }
        if (precoMaximo != null) {
            jpql.append(" AND preco <= :precoMaximo");
            params.put("precoMaximo", precoMaximo);
        }
        if (cordasMinimo != null) {
            jpql.append(" AND cordas >= :cordasMinimo");
            params.put("cordasMinimo", cordasMinimo);
        }
        if (cordasMaximo != null) {
            jpql.append(" AND cordas <= :cordasMaximo");
            params.put("cordasMaximo", cordasMaximo);
        }
        if (fabricadaApos != null) {
            jpql.append(" AND fabricacao >= :fabricadaApos");
            params.put("fabricadaApos", fabricadaApos);
        }
        if (fabricadaAntes != null) {
            jpql.append(" AND fabricacao <= :fabricadaAntes");
            params.put("fabricadaAntes", fabricadaAntes);
        }

        if (params.isEmpty()) {
            return listAll();
        }

        return find(jpql.toString(), params).list();
    }

}
