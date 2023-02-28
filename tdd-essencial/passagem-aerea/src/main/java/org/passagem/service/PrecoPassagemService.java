package org.passagem.service;

import org.passagem.exception.TipoPassageiroInvalidoException;
import org.passagem.model.Passageiro;
import org.passagem.model.Voo;

import static org.passagem.model.TipoPassageiro.GOLD;
import static org.passagem.model.TipoPassageiro.SILVER;

public class PrecoPassagemService {
    public double calcular(Passageiro passageiro, Voo voo) throws TipoPassageiroInvalidoException {
        if(passageiro.getTipo().equals(GOLD)) {
            if (voo.getPreco() > 500)
                return voo.getPreco() * 0.85;
            return voo.getPreco() * 0.9;
        }
        else if(passageiro.getTipo().equals(SILVER)){
            if(voo.getPreco() > 700)
                return voo.getPreco() * 0.90;
            return voo.getPreco() * 0.94;
        }
        throw new TipoPassageiroInvalidoException();
    }
}
