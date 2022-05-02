package nextstep.ladder.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("참가자들과 결과들")
class ParticipantsWithResultsTest {

    private static final Participants TWO_PARTICIPANTS = Participants.from(Arrays.asList("a", "b"));
    private static final Results TWO_RESULTS = Results.from(Arrays.asList("100", "500"));

    @Test
    @DisplayName("참가자들과 결과들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS));
    }

    @Test
    @DisplayName("참가자들과 결과들은 필수")
    void instance_nullArguments_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> ParticipantsWithResults.of(TWO_PARTICIPANTS, null));
        assertThatIllegalArgumentException().isThrownBy(() -> ParticipantsWithResults.of(null, TWO_RESULTS));
    }

    @Test
    @DisplayName("참가자들과 결과들의 사이즈는 동일")
    void instance_differentSize_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> ParticipantsWithResults.of(Participants.from(Arrays.asList("a", "b", "c")), TWO_RESULTS));
    }

    @Test
    @DisplayName("참가자 존재 여부")
    void isNotExistParticipant() {
        //given
        ParticipantsWithResults environment = ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS);
        //when, then
        assertThat(environment.isNotExistParticipant(Participant.from("a"))).isFalse();
        assertThat(environment.isNotExistParticipant(Participant.from("c"))).isTrue();
    }

    @Test
    @DisplayName("참가자 존재 여부")
    void participantIndexOf() {
        //given
        ParticipantsWithResults environment = ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS);
        //when, then
        assertThat(environment.participantIndexOf(Participant.from("a"))).isZero();
        assertThat(environment.participantIndexOf(Participant.from("c"))).isEqualTo(-1);
    }

    @Test
    @DisplayName("인덱스로 결과 가져오기")
    void result() {
        assertThat(ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS).result(0)).isEqualTo(Result.from("100"));
    }

    @Test
    @DisplayName("주어진 참가자들 그대로 반환")
    void participants() {
        assertThat(ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS).participants()).isEqualTo(TWO_PARTICIPANTS);
    }

    @Test
    @DisplayName("주어진 결과들 그대로 반환")
    void results() {
        assertThat(ParticipantsWithResults.of(TWO_PARTICIPANTS, TWO_RESULTS).results()).isEqualTo(TWO_RESULTS);
    }
}
