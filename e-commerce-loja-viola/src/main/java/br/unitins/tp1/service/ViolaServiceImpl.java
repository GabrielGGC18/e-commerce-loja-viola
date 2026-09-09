package br.unitins.tp1.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.Viola;
import br.unitins.tp1.repository.ViolaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ViolaServiceImpl implements ViolaService {

    @Inject
    ViolaRepository repository;

    @Override
    @Transactional
    public Viola create(Viola viola) {
        repository.persist(viola);
        return viola;
    }

    @Override
    @Transactional
    public void update(Long id, Viola viola) {
        Viola violaBanco = repository.findById(id);
        if (violaBanco == null) {
            throw new RuntimeException("Viola não encontrada");
        }
        violaBanco.setModelo(viola.getModelo());
        violaBanco.setMarca(viola.getMarca());
        violaBanco.setAfinacao(viola.getAfinacao());
        violaBanco.setFabricacao(viola.getFabricacao());
        violaBanco.setPreco(viola.getPreco());
        violaBanco.setCordas(viola.getCordas());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Viola findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Viola> findAll() {
        return repository.listAll();
    }

    @Override
    public List<Viola> findByModelo(String modelo) {
        return repository.findByModelo(modelo);
    }

    @Override
    public List<Viola> findByMarca(String marca) {
        return repository.findByMarca(marca);
    }

    @Override
    public List<Viola> findByAfinacao(String afinacao) {
        return repository.findByAfinacao(afinacao);
    }

    @Override
    public List<Viola> findByPreco(Double precoMinimo, Double precoMaximo) {
        return repository.findByPreco(precoMinimo, precoMaximo);
    }

    @Override
    public List<Viola> findByCordas(Integer cordasMinimo, Integer cordasMaximo) {
        return repository.findByCordas(cordasMinimo, cordasMaximo);
    }

    @Override
    public List<Viola> findByFabricacao(LocalDate inicio, LocalDate fim) {
        return repository.findByFabricacao(inicio, fim);
    }

    @Override
    public List<Viola> findByFiltros(String modelo, String marca, String afinacao,
            Double precoMinimo, Double precoMaximo, Integer cordasMinimo, Integer cordasMaximo,
            LocalDate fabricadaApos, LocalDate fabricadaAntes) {
        return repository.findByFiltros(modelo, marca, afinacao, precoMinimo, precoMaximo,
                cordasMinimo, cordasMaximo, fabricadaApos, fabricadaAntes);
    }

}
