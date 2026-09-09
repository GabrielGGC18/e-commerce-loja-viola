package br.unitins.tp1.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.Viola;

public interface ViolaService {
    Viola create(Viola viola);
    void update(Long id, Viola viola);
    void delete(Long id);
    Viola findById(Long id);
    List<Viola> findAll();
    List<Viola> findByModelo(String modelo);
    List<Viola> findByMarca(String marca);
    List<Viola> findByAfinacao(String afinacao);
    List<Viola> findByPreco(Double precoMinimo, Double precoMaximo);
    List<Viola> findByCordas(Integer cordasMinimo, Integer cordasMaximo);
    List<Viola> findByFabricacao(LocalDate inicio, LocalDate fim);
    List<Viola> findByFiltros(String modelo, String marca, String afinacao,
            Double precoMinimo, Double precoMaximo, Integer cordasMinimo, Integer cordasMaximo,
            LocalDate fabricadaApos, LocalDate fabricadaAntes);
}
