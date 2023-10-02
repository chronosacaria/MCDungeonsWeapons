package chronosacaria.mcdw.api.interfaces;

import java.util.List;

public interface IExclusiveAOECloud {

    @SuppressWarnings("unused")
    List<Boolean> mcdw$getExclusions();
    void mcdw$setExclusions(boolean owner, boolean allies, boolean enemy);
}