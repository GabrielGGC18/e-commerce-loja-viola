package br.unitins.tp1.resource;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.Viola;
import br.unitins.tp1.service.ViolaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/violas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ViolaResource {

    @Inject
    ViolaService service;

    @GET
    public List<Viola> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Viola buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/modelo/{modelo}")
    public List<Viola> buscarPorModelo(@PathParam("modelo") String modelo) {
        return service.findByModelo(modelo);
    }

    @GET
    @Path("/marca/{marca}")
    public List<Viola> buscarPorMarca(@PathParam("marca") String marca) {
        return service.findByMarca(marca);
    }

    @GET
    @Path("/afinacao/{afinacao}")
    public List<Viola> buscarPorAfinacao(@PathParam("afinacao") String afinacao) {
        return service.findByAfinacao(afinacao);
    }

    @GET
    @Path("/preco")
    public List<Viola> buscarPorPreco(@QueryParam("min") Double min, @QueryParam("max") Double max) {
        return service.findByPreco(min == null ? 0d : min, max == null ? Double.MAX_VALUE : max);
    }

    @GET
    @Path("/cordas")
    public List<Viola> buscarPorCordas(@QueryParam("min") Integer min, @QueryParam("max") Integer max) {
        return service.findByCordas(min == null ? 0 : min, max == null ? Integer.MAX_VALUE : max);
    }

    @GET
    @Path("/fabricacao")
    public List<Viola> buscarPorFabricacao(@QueryParam("inicio") String inicio, @QueryParam("fim") String fim) {
        LocalDate dataInicio = (inicio == null || inicio.isBlank()) ? LocalDate.of(1900, 1, 1) : LocalDate.parse(inicio);
        LocalDate dataFim = (fim == null || fim.isBlank()) ? LocalDate.now() : LocalDate.parse(fim);
        return service.findByFabricacao(dataInicio, dataFim);
    }

    @GET
    @Path("/filtros")
    public List<Viola> buscarPorFiltros(
            @QueryParam("modelo") String modelo,
            @QueryParam("marca") String marca,
            @QueryParam("afinacao") String afinacao,
            @QueryParam("precoMin") Double precoMin,
            @QueryParam("precoMax") Double precoMax,
            @QueryParam("cordasMin") Integer cordasMin,
            @QueryParam("cordasMax") Integer cordasMax,
            @QueryParam("fabricadaApos") String fabricadaApos,
            @QueryParam("fabricadaAntes") String fabricadaAntes) {

        LocalDate apos = (fabricadaApos == null || fabricadaApos.isBlank()) ? null : LocalDate.parse(fabricadaApos);
        LocalDate antes = (fabricadaAntes == null || fabricadaAntes.isBlank()) ? null : LocalDate.parse(fabricadaAntes);

        return service.findByFiltros(modelo, marca, afinacao, precoMin, precoMax, cordasMin, cordasMax, apos, antes);
    }

    @POST
    public Viola inserir(Viola viola) {
        return service.create(viola);
    }

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, Viola viola) {
        service.update(id, viola);
    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        service.delete(id);
    }

}
