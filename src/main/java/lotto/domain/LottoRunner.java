package lotto.domain;

public class LottoRunner {

	private LottoNumbers lottoNumbers;
	private LottoMatchResults lottoMatchResults;

	private static final int PRICE_PER_LOTTO_NUMBER = 1000;

	public LottoRunner(long payment) {
		this.lottoMatchResults = new LottoMatchResults(payment);
		this.lottoNumbers = LottoNumbersGenerator.generate(lottoTotalAmount(payment));
	}

	public LottoMatchResults match(WinningNumber winningNumber) {
		return this.lottoNumbers.match(this.lottoMatchResults, winningNumber);
	}

	public long lottoTotalAmount(long payment) {
		return payment / PRICE_PER_LOTTO_NUMBER;
	}

	public LottoNumbers getLottoNumbers() {
		return this.lottoNumbers;
	}
}