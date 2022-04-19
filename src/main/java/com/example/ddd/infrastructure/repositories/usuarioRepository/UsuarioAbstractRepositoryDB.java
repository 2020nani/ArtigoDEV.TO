package com.example.ddd.infrastructure.repositories.usuarioRepository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.ddd.core.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioAbstractRepositoryDB {

    private DynamoDBMapper dynamoDBMapper;


    @Autowired
    public UsuarioAbstractRepositoryDB(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Usuario buscaUsuarioPorId(String usuarioId) {
        return dynamoDBMapper.load(Usuario.class, usuarioId);
    }

   public List<Usuario> buscaUsuarios() {

       final DynamoDBScanExpression paginatedScanListExpression = new DynamoDBScanExpression();
       final PaginatedScanList<Usuario> usuarioList = dynamoDBMapper.scan(Usuario.class, paginatedScanListExpression);
       return usuarioList;
       /* Using 'PaginatedScanList' 
       Map<String, AttributeValue> exclusiveStartKeyEvaluated = Map.of("usuarioId", new AttributeValue("d037386c-f201-4ce1-9825-51a5f1bbc6b1"));
       final DynamoDBScanExpression paginatedScanListExpression = new DynamoDBScanExpression()
               .withExclusiveStartKey(exclusiveStartKeyEvaluated)
               .withLimit(5);
       System.out.println(paginatedScanListExpression.getExclusiveStartKey());
       final PaginatedScanList<Usuario> paginatedList = dynamoDBMapper.scan(Usuario.class, paginatedScanListExpression);
       paginatedList.forEach(System.out::println);
       return paginatedList;

       // using 'ScanResultPage'
       final DynamoDBScanExpression scanPageExpression = new DynamoDBScanExpression()
               .withLimit(5);
       do {
           ScanResultPage<Usuario> scanPage = dynamoDBMapper.scanPage(Usuario.class, scanPageExpression);
           scanPage.getResults().forEach(System.out::println);
           System.out.println("LastEvaluatedKey=" + scanPage.getLastEvaluatedKey());
           scanPageExpression.setExclusiveStartKey(scanPage.getLastEvaluatedKey());

       } while (scanPageExpression.getExclusiveStartKey() != null);*/

   }

    public String deletaUsuario(String usuarioId){
        Usuario usuario = dynamoDBMapper.load(Usuario.class,usuarioId);
        dynamoDBMapper.delete(usuario);
        return "Usuario deletado com sucesso";
    }

    public Usuario criaUsuario(Usuario usuario) {
        dynamoDBMapper.save(usuario);
        return usuario;
    }

    public Usuario updateUsuario(String usuarioId, Usuario usuario) {

        dynamoDBMapper.save(usuario,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("usuarioId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(usuarioId)
                                )));
        Usuario usuarioAtualizado = buscaUsuarioPorId(usuarioId);
        return usuarioAtualizado;
    }
}
