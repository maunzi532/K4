package kampf.k2;

import effektkarten.effekte.wirkung.Wirkung;
import effektkarten.effekte.ziel.EndTrigger;

public record AktiverEffekt2(Wirkung wirkung, int wirkungDaten, EndTrigger endTrigger, int wirkungsDauer)
{
}
