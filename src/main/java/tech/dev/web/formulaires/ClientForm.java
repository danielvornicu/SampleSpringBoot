package tech.dev.web.formulaires;

import tech.dev.to.ClientTO;
import tech.dev.web.common.base.BaseForm;

/**
 * Description de la classe
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class ClientForm extends BaseForm<ClientTO> {

    private ClientTO client;

    @Override
    public void initForm(ClientTO client) {
        if(client == null)
            client = new ClientTO();
        this.client = client;
    }

    @Override
    public ClientTO saveForm() {
        return client;
    }

    public ClientTO getClient() {
        return client;
    }

    public void setClient(ClientTO client) {
        this.client = client;
    }
}
