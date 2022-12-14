package lpka.prj.crystallis.domain.commons.session;

import lpka.prj.crystallis.domain.commons.session.input.SessionApplyInput;

import java.util.List;

public interface Session<T> {
    void nextStage(List<SessionApplyInput<T>> inputs);
    void backStage();
    void apply(SessionApplyInput<T> input);
}
