package kampf.k2;

import effektkarten.effekte.eigenschaften.*;
import effektkarten.effekte.ziel.*;

public record AktiverEffekt2(Wirkung wirkung, int wirkungDaten, EndTrigger endTrigger, int wirkungsDauer)
{
}
