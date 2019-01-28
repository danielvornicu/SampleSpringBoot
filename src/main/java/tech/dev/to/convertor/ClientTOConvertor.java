package tech.dev.to.convertor;

import tech.dev.entites.Client;
import tech.dev.commons.to.base.convertor.TOConvertor;
import tech.dev.to.ClientTO;

/**
 * Description de la classe
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class ClientTOConvertor extends TOConvertor<Client, ClientTO> {

    @Override
    public ClientTO entity2TO(Client client, boolean fillAll) {

        ClientTO to = new ClientTO();

        to.setId(client.getId());
        to.setNom(client.getNom());
        to.setPrenom(client.getPrenom());

        return to;
    }

    @Override
    public Client fillEntity(ClientTO to, Client client) {

        if (client == null) {
            client = new Client();
        }

        client.setNom(to.getNom());
        client.setPrenom(to.getPrenom());

        return client;
    }

}
