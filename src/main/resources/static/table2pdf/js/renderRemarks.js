function renderRemarks(independentPage, footer) {
	const html = `<div class="h-[600px] w-full pt-[74px]">
      <div class="font-bold text-[28px] leading-[28px] pb-[14px]">
        <em>
          Note: UANs are prefixed with Asterisk sign (*) in case AADHAAR is not seeded /unverified
        </em>
      </div>
      <div class="flex">
        <div class="mr-[21px] flex flex-col">
          <div class="text-[28px] leading-[28px] pb-[47px]">
            PMRPY Benefit Not Given Remarks :-
          </div>
          <div class="flex h-[444px] flex-col text-[28px]">
            <div class="flex flex-1 font-bold">
              <div
                class="w-[279px] border font-bold border-black flex justify-center items-center"
              >
                Reason Code
              </div>
              <div
                class="w-[722px] border font-bold border-black  border-l-0 flex justify-center items-center"
              >
                Reason Name
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black flex justify-center border-t-0 items-center">
                EC10001
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                ECR already filed for this member
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                EC10002
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Parallel Employment: ECR already filed for this
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black flex justify-center items-center">
                EC10003
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Benefit already availed for this member
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black flex justify-center border-t-0 items-center">
                EC10004
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Gross/EPF wages greater than 15,000/-
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black flex justify-center border-t-0 items-center">
                EC10005
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Mismatch in EPF and EPS wages
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                EC10006
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Mismatch in Due and Remitted values
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                EC10007
              </div>
              <div class="w-[722px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                UAN Deactivated
              </div>
            </div>
          </div>
        </div>
        <div class="flex flex-col">
          <div class="text-[28px] leading-[28px] pb-[47px]">
          ABRY Benefit Not Given Remarks :-
          </div>
          <div class="flex h-[444px] flex-col text-[28px]">
            <div class="flex flex-1 font-bold">
              <div
                class="w-[279px] border font-bold border-black flex justify-center items-center"
              >
                Reason Code
              </div>
              <div
                class="w-[931px] border font-bold border-black  border-l-0 flex justify-center items-center"
              >
                Reason Name
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10001
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                EPF wages are greatter than or equal to 15,000/-
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10002
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Mismatch in EPF and EPS wages
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10003
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                EPF contribution remitted is greatter than due remittance
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10004
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                EPS contribution remitted is greatter than due remittance
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10005
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                (EPF - EPS) diffrence contribution remitted is greatter than due
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10006
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                EPS contribution remitted is greatter than due remittance
              </div>
            </div>
            <div class="flex flex-1 ">
              <div class="w-[279px] border border-black border-t-0 flex justify-center items-center">
                GK10007
              </div>
              <div class="w-[931px] border pl-[12px] border-black  border-l-0 border-t-0 flex items-center">
                Aadhaar not seeded
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>`
	if (independentPage) {
		return html
	} else {
		return `<div class="w-[2339px] h-[1653px] relative pl-[55px]">
      ${footer}
      ${html}
    </div>
    `
	}
}

