package lpka.prj.crystallis.domain.commons.session.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@AllArgsConstructor(staticName = "of")
@Data
public class SessionApplyInput<T> {
    protected final T enumeration;
    protected final Consumer<Object> cons;
    protected Object data;
}
