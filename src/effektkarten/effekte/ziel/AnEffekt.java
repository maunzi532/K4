package effektkarten.effekte.ziel;

import effektkarten.effekte.wirkung.Wirkung;

public record AnEffekt(Wirkung wirkung, EndTrigger endTrigger, int dauer){}