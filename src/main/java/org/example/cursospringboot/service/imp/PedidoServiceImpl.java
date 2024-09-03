package org.example.cursospringboot.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.cursospringboot.domain.entity.Cliente;
import org.example.cursospringboot.domain.entity.ItemPedido;
import org.example.cursospringboot.domain.entity.Pedido;
import org.example.cursospringboot.domain.entity.Produto;
import org.example.cursospringboot.domain.enums.StatusPedido;
import org.example.cursospringboot.domain.repository.ClientesRepository;
import org.example.cursospringboot.domain.repository.ItemsPedidoRepository;
import org.example.cursospringboot.domain.repository.PedidosRepository;
import org.example.cursospringboot.domain.repository.ProdutosRepository;
import org.example.cursospringboot.exception.PedidoNaoEncontradoException;
import org.example.cursospringboot.exception.RegraNegocioException;
import org.example.cursospringboot.rest.dto.ItemPedidoDTO;
import org.example.cursospringboot.rest.dto.PedidoDTO;
import org.example.cursospringboot.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository repository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemsPedidoRepository itemsRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto)  {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(
                        () -> new RegraNegocioException("Código do cliente inválido")
                );

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itens = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itemsRepository.saveAll(itens);
        pedido.setItens(itens);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizarStatus(Integer id, StatusPedido status) {
        repository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(status);
                    return repository.save(pedido);
                }).orElseThrow(PedidoNaoEncontradoException::new);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é permitido criar um pedido sem itens");
        }

        return itens
            .stream()
            .map(dto -> {
                Integer idProduto = dto.getProduto();
                Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(
                            () -> new RegraNegocioException("Código do produto inválido")
                    );

                ItemPedido item = new ItemPedido();
                item.setQuantidade(dto.getQuantidade());
                item.setPedido(pedido);
                item.setProduto(produto);
                return item;
            }).collect(Collectors.toList());
    }

}
